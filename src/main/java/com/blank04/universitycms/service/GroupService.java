package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        Optional<Group> entity = findById(id);
        if (entity.isPresent()) {
            groupRepository.deleteById(id);
        } else throw new SQLException("Group with id="+ id + " doesn't exist");
    }

    @Transactional
    public List<Student> findRelatedStudents(Long groupId) {
        Optional<Group> group = findById(groupId);
        if (group.isPresent()) {
            return group.get().getStudents();
        } else throw new IllegalArgumentException(String.format("Group with id=%d doesn't exist", groupId));
    }
}
