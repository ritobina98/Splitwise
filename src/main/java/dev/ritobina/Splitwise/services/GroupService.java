package dev.ritobina.Splitwise.services;

import dev.ritobina.Splitwise.dtos.GroupCreationReqDTO;
import dev.ritobina.Splitwise.exceptions.GroupNotFoundException;
import dev.ritobina.Splitwise.exceptions.UserNotFoundException;
import dev.ritobina.Splitwise.models.Group;
import dev.ritobina.Splitwise.models.Transaction;
import dev.ritobina.Splitwise.models.User;
import dev.ritobina.Splitwise.repositories.GroupRepository;
import dev.ritobina.Splitwise.services.strategy.MaxMinSettleUpStrategy;
import dev.ritobina.Splitwise.services.strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;

    public Group creategroup(GroupCreationReqDTO groupCreationReqDTO){
        Group group = new Group();
        group.setName(groupCreationReqDTO.getGroupName());
        List<User> users = new ArrayList<>();
        for(Integer id : groupCreationReqDTO.getMemberIds()){
            users.add(userService.getUserById(id));
        }
        group.setUsers(users);
        return groupRepository.save(group);
    }

    public Group getGroupById(Integer id){
        return groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with id "+id+" is not found")
        );
    }

    public String settleGroup(int groupId){
        SettleUpStrategy settleUpStrategy = new MaxMinSettleUpStrategy();
        Group group = getGroupById(groupId);
        List<Transaction> transactions = settleUpStrategy.settleUp(group);
        return transactions.toString();
    }
}
