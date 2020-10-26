import axios from 'axios';
import React,{useState} from "react";
import {Link, useHistory} from "react-router-dom";
import Notifications, { notify } from 'react-notify-toast';
import "../../styles/login.css";

function Register(){

    const [name, SetName] = useState("");
    const [email, SetEmail] = useState("");
    const [password, SetPassword] = useState("");
    const [photo, SetPhoto] = useState("");

    const updateEmail = ev => SetEmail(ev.target.value);
    const updatePassword = ev => SetPassword(ev.target.value);
    const updateName = ev => SetName(ev.target.value);
    const updatePhoto = ev =>SetPhoto(ev.target.value);

    let history = useHistory();
    let myColor = { background: '#0E1717', text: "#FFFFFF" };

    function goToLogin(){
        history.push("/login");
    }

    function register(ev){
        ev.preventDefault()
        if(validateForm()){
            executeRequest()
        }
    }

    function validateForm(){
        if(incompleteFields()){
            showErrorMessage("* Please complete all the fields.", "error")
        } else if (!validEmail()){
            showErrorMessage("* The email in not valid, please try", "error")
        } 
        return (!incompleteFields() && validEmail() )
        }

    function showErrorMessage(msjError, type){
        notify.show(msjError,type,2500, myColor);
    }

    function incompleteFields(){
        return !name || !email || !password || !photo
    }

    function validEmail(){
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function executeRequest(){
        axios.post("http://localhost:7000/register",
        {
            name: name,
            email: email,
            password: password,
            photo: photo
        }
        )
        .then(success => {
            goToLogin();
        })
        .catch(error => {
            console.log(error.response.data)
            showErrorMessage("*Email is used", error.response.data.message)
        })
    }

    return (
        <form className="signInForm">
            <Notifications />
            <input className="inp" type="text" placeholder="Name" name="name"
                value={name}
                onChange={updateName}
                required />
            <input className="inp" type="email" placeholder="Email" name="email"
                value={email}
                onChange={updateEmail}
                required autoFocus />
            <input className="inp" type="password" placeholder="Password" name="password"
                value={password}
                onChange={updatePassword}
                required />
            <input className="inp" type="text" placeholder="PhotoLink" name="photoLink"
                value={photo}
                onChange={updatePhoto}
                required />
            <button className="btnw" type="button" onClick={event => register(event)} >Register</button>
        </form>);
}

export default Register;