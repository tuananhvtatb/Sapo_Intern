import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import { getToken } from '../Utils/Common'
import { removeUserSession} from '../Utils/Common'

export default class Home extends Component {

  buttonLogin(){
    removeUserSession();
    this.props.history.push('/login');
  }

  render() {
    
    var token = getToken();
    return (
      <div>
        <h1>Welcome to Home Page!</h1>
        <br></br>
        <button className="btn btn-primary" onClick={() => {this.buttonLogin()}} >{token !== null ? 'Logout' : 'Login'}</button>
        <Link to="/categories" className="btn btn-success ml-5">Danh mục</Link>
      </div>
    )
  }
}

