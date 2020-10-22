import axios from 'axios';
import React,{useState} from "react";
import {Link, useHistory} from "react-router-dom";

function Register(){

    const [name, SetName] = useState("");
    const [email, SetEmail] = useState("");
    const [password, SetPassword] = useState("");
    const [photo, SetPhoto] = useState("");

    const updateEmail = ev => SetEmail(ev.target.value);
    const updatePassword = ev => SetPassword(ev.target.value);
    const updateName = ev => SetName(ev.target.value);
    const updatePhoto = ev =>SetPhoto(ev.target.value);

    



}