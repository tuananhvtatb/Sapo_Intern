import React, { Component } from 'react'
import { setUserSession } from '../Utils/Common';

export default class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username:'',
      password:'',
      err: ''
    };
  }

  login(e) {
    e.preventDefault();
    var username = e.target.elements.username.value;
    var password = e.target.elements.password.value;

    if (username === 'abc' && password === '123') {
      setUserSession('token an danh', { username, password });
      this.props.history.push('/categories');
    } else {
      this.setState({
        err: 'Username hoặc passwword không đúng!'
      });
    }
  }

  render() {
    return (
      <div className="container">
        <h3>Login</h3>
        <form onSubmit={this.login.bind(this)} method="post">
          <div className="form-group">
            <label>Username</label>
            <input type="text" className="form-control" name="username" id="username" aria-describedby="emailHelpId" placeholder="Email" required />

          </div>
          <br />
          <div className="form-group">
            <label>Password</label>
            <input type="password" className="form-control" name="password" id="password" placeholder="Password" required />
          </div>
          <br />
          <input type="submit" value="Login" />
          <br></br>
          {this.state.err !== '' ? this.state.err : ''}

        </form>
      </div>
    );
  }
}
