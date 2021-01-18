import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import DashboardComponent from './dashboard'
import LoginComponent from './login';

class IndexComponent extends React.Component{

    render(){
        return(
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={LoginComponent} />
                    <Route exact path="/welcome/:username" component={DashboardComponent} />
                </Switch>
            </BrowserRouter>
        );
    }
}
export default IndexComponent;