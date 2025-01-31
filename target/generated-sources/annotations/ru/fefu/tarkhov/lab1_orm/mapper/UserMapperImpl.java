package ru.fefu.tarkhov.lab1_orm.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.fefu.tarkhov.lab1_orm.dto.UserDto;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateUserCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateUserCommand;
import ru.fefu.tarkhov.lab1_orm.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-09T18:46:15+1000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserDto source) {
        if ( source == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( source.getId() );
        user.email( source.getEmail() );
        user.birthDate( source.getBirthDate() );

        return user.build();
    }

    @Override
    public User map(CreateUserCommand source) {
        if ( source == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( source.getEmail() );
        user.birthDate( source.getBirthDate() );
        user.sex( source.getSex() );

        return user.build();
    }

    @Override
    public User map(UpdateUserCommand source) {
        if ( source == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( source.getId() );
        user.email( source.getEmail() );
        user.birthDate( source.getBirthDate() );
        user.sex( source.getSex() );

        return user.build();
    }

    @Override
    public UserResponse map(User source) {
        if ( source == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( source.getId() );
        userResponse.setEmail( source.getEmail() );
        userResponse.setBirthDate( source.getBirthDate() );
        userResponse.setSex( source.getSex() );

        return userResponse;
    }
}
