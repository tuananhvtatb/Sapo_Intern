import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom'
import CategoryDetail from './CategoryDetail';

import CategoryList from './CategoryList';

class IndexComponent extends React.Component{

    render(){
        return(
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={CategoryList} />
                    <Route exact path="/category/:id" component={CategoryDetail} />
                </Switch>
            </BrowserRouter>
        );
    }
}
export default IndexComponent;