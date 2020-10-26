import React from 'react';
import Home from '../components/Home/home';
import Login from '../components/Login/Login';
import Register from '../components/Login/Register';

import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Note from '../components/Note/note';
import SearchNote from '../components/Search/SearchNote';
import SearchCategory from '../components/Search/SearchCategory';


export default function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>  
                <Route exact path="/login" component ={ Login } />
                <Route exact path="/register" component={ Register }/>
                <Route exact path= "/content/:id" component= {Note}/>
                <Route exact path="/search/:search" component={SearchNote}/>
                <Route exact path="/category/:name" component={SearchCategory}/>
            </Switch>
        </Router>        
    );
}


