import React from 'react';
import Home from '../components/Home/home';
import Login from '../components/Login/Login';

import {BrowserRouter as Router, Redirect, Switch, Route, useHistory} from 'react-router-dom';

export default function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>  
                <Route exact path="/login" component ={ Login } />
            </Switch>
        </Router>        
    );
}


