package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.GroupRepository;
import com.blank04.universitycms.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws SQLException {
        Optional<Group> entity = findById(id);
        if (entity.isPresent()) {
            groupRepository.deleteById(id);
        } else throw new SQLException("Group with id="+ id + " doesn't exist");
    }

    @Transactional
    @Override
    public List<Student> findRelatedStudents(Long groupId) {
        Optional<Group> group = findById(groupId);
        if (group.isPresent()) {
            return group.get().getStudents();
        } else throw new IllegalArgumentException(String.format("Group with id=%d doesn't exist", groupId));
    }
}
