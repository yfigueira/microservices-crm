package org.example.activityservice.activity.persistence;

import org.example.activityservice.activity.domain.Activity;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityMapper {

    Activity toDomain(ActivityEntity entity);

    ActivityEntity toEntity(Activity domain);

    default ActivityStatus mapActivityStatus(int id) {
        return ActivityStatus.builder().id(id).build();
    }

    default int mapActivityStatus(ActivityStatus status) {
        return status.id();
    }

    default ActivityType mapActivityType(int id) {
        return ActivityType.builder().id(id).build();
    }

    default int mapActivityType(ActivityType activityType) {
        return activityType.id();
    }

    default EntityType mapEntityType(int id) {
        return EntityType.builder().id(id).build();
    }

    default int mapEntityType(EntityType entityType) {
        return entityType.id();
    }

    default User mapOwner(UUID id) {
        return User.builder().id(id).build();
    }

    default UUID mapOwner(User user) {
        return user.id();
    }
}
