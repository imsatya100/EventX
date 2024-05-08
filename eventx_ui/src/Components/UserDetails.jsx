import React, { useState, useEffect } from 'react';
import axios from 'axios';
const API_URL = "http://192.168.10.166:8040/api/v1/users/"
const UserDetails = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get(API_URL)
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error('Error fetching users: ', error);
            });
    }, []);

    return (
        <div>
            <h1>User List</h1>
            <ul>
                {users.map(user => (
                    <li key={user.userId}>
                       {user.userId} - {user.firstName} {user.lastName} - {user.email} - {user.statusMain} - {user.createdDate} - {user.modifiedDate}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserDetails;
