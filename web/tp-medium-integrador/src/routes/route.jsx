import React from 'react';
import Home from '../components/Home/home';

import {BrowserRouter as Router, Redirect, Switch, Route, useHistory} from 'react-router-dom';

export default function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>   
            </Switch>
        </Router>        
    );
}


