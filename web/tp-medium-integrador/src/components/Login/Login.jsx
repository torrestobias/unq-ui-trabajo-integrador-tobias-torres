import axios from "axios";
import React,{ useState } from "react";
import { Link, useHistory } from "react-router-dom";
import "../../styles/login.css";
import Notifications, {notify} from 'react-notify-toast';


function Login(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const updateEmail = ev => setEmail(ev.target.value);
    const updatePassword = ev => setPassword(ev.target.value);

    let history = useHistory();
    let myColor = { background: '#0E1717', text: "#FFFFFF" };

    function goHome(){
        history.push("/");
    }

    function getUser(){
        axios.get("http://localhost:7000/user").then(success=>{
            localStorage.setItem('author', success.data.name);
        }).catch(error => {
            console.log("error get user", error.response);
        });
    }

    function signIn(ev){
        axios.post("http://localhost:7000/login",
        {
            email: email,
            password: password
        },
        )
        .then(success =>{
                console.log("success", success);
                localStorage.setItem('tokenValido', success.headers.authorization);
                axios.defaults.headers['authorization'] = localStorage.getItem('tokenValido')
                getUser();
                goHome();
            }
        )
        .catch(error => {
            console.log("login error", error.response.data.title);
            const errorLabel = error.response.data.message;
            notify.show(errorLabel, "warning", 5000, myColor);        
        });
    }



    return (
        <form className="signInForm">
            <Notifications/>
            <input className="inp" type="text" placeholder="Username" name="uname"
                   value={email}
                   onChange={updateEmail}
                   required/>
            <input className="inp" type="password" placeholder="Password" name="psw"
                   value={password}
                   onChange={updatePassword}
                   required/>
            <button className="btnw" type="button" onClick={signIn}>Login</button>
            <span className="regis"> <Link to="/register">Registrar</Link></span>
            
        </form>
    )

}

export default Login;