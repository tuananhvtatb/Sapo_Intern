import { BrowserRouter, Switch, Route} from 'react-router-dom';

import Login from './Component/Login';
import Home from './Component/Home';

import PrivateRoute from './Utils/PrivateRoute';
import PublicRoute from './Utils/PublicRoute';
import CategoryList from './Component/CategoryList';
import AddCategory from './Component/AddCategory';

function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <div>
          <div className="content">
            <Switch>
              <Route exact path="/" component={Home} />
              <PublicRoute path="/login" component={Login} />
              <PrivateRoute exact path="/categories" component={CategoryList} />
              <PrivateRoute exact path = "/categories/add" component={AddCategory}/>
              <PrivateRoute  exact path = "/categories/add/:id" component={AddCategory}/>
            </Switch>
          </div>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
