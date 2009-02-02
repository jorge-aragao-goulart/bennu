/*
 * @(#)User.java
 *
 * Copyright 2009 Instituto Superior Tecnico, João Figueiredo, Luis Cruz, Paulo Abrantes, Susana Fernandes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the MyOrg web application infrastructure.
 *
 *   MyOrg is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.*
 *
 *   MyOrg is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with MyOrg. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package myorg.domain;

import java.util.Comparator;

import myorg.domain.groups.People;
import myorg.domain.groups.Role;

public class User extends User_Base {

    public static final Comparator<User> COMPARATOR_BY_NAME = new Comparator<User>() {

	@Override
	public int compare(final User user1, final User user2) {
	    return user1.getUsername().compareTo(user2.getUsername());
	}
	
    };

    public User() {
        super();
        setMyOrg(MyOrg.getInstance());
    }

    public User(final String username) {
	this();
	setUsername(username);
    }

    public static User findByUsername(final String username) {
	for (final User user : MyOrg.getInstance().getUserSet()) {
	    if (user.getUsername().equalsIgnoreCase(username)) {
		return user;
	    }
	}
	return null;
    }

    public boolean hasRoleType(final RoleType roleType) {
	for (final People people : getPeopleGroupsSet()) {
	    if (people instanceof Role) {
		final Role role = (Role) people;
		if (role.getRoleType() == roleType) {
		    return true;
		}
	    }
	}
	return false;
    }

}
