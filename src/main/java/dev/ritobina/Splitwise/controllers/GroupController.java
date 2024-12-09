package dev.ritobina.Splitwise.controllers;

import dev.ritobina.Splitwise.dtos.GroupCreationReqDTO;
import dev.ritobina.Splitwise.models.Group;
import dev.ritobina.Splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity createGroup(@RequestBody GroupCreationReqDTO groupCreationReqDTO){
        Group savedGroup = groupService.creategroup(groupCreationReqDTO);
        return ResponseEntity.ok(savedGroup);
    }

    //settle all expenses in the group -> generate minimum number of transactions to settle the group
    @PostMapping("/group/{id}/settle")
    public ResponseEntity settleGroup(@PathVariable("id") int groupId){
        return ResponseEntity.ok(groupService.settleGroup(groupId));
    }
}
