import React from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

function Navbar (){

    const [search, SetSearch] = useState(" ");

    const update = ev => SetSearch (ev.target.value);

    const verifySearch = () =>{
            if (search === ""){
            SetSearch(" ")
        }
    }


    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            
            <Link className="navbar-brand" to="/">Medium</Link>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <Link className="nav-link" to="/login">Sign in</Link>
                    </li>
                    <li className="nav-item active">
                        <Link className="nav-link" to="/register">Sign up</Link>
                    </li>
                </ul>
                <form className="form-inline my-2 my-lg-0">
                    <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" onChange={update}/>
                    <Link to={"/search/"+search}><button className="btn btn-outline-success my-2 my-sm-0" type="submit" onChange={verifySearch()}>Search</button></Link>
                </form>
            </div>
            </nav>
    )
}

export default Navbar;