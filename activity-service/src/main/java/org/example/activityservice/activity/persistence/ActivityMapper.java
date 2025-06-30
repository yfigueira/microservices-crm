package org.example.activityservice.activity.persistence;

import org.example.activityservice.activity.domain.Activity;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityMapper {

    Activity toDomain(ActivityEntity entity);

    ActivityEntity toEntity(Activity domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ActivityEntity updateEntity(Activity domain, @MappingTarget ActivityEntity entity);

    default ActivityStatus mapActivityStatus(Integer id) {
        return ActivityStatus.builder().id(id).build();
    }

    default Integer mapActivityStatus(ActivityStatus status) {
        return status.id();
    }

    default ActivityType mapActivityType(Integer id) {
        return ActivityType.builder().id(id).build();
    }

    default Integer mapActivityType(ActivityType activityType) {
        return activityType.id();
    }

    default EntityType mapEntityType(Integer id) {
        return EntityType.builder().id(id).build();
    }

    default Integer mapEntityType(EntityType entityType) {
        return entityType.id();
    }

    default User mapOwner(UUID id) {
        return User.builder().id(id).build();
    }

    default UUID mapOwner(User user) {
        return user.id();
    }
}
