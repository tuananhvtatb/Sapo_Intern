import React from 'react';


class LoginComponent extends React.Component {

    constructor(props){
        super(props);
        console.log(props);
        this.state={
            err:''
        };
    }

    login(e){
        e.preventDefault();
        var username = e.target.elements.username.value;
        var password = e.target.elements.password.value;

        if(username === 'abc' && password === '123'){
            this.props.history.push('/welcome/'+username);
            
        }else{
            this.setState({
                err:'Username hoặc passwword không đúng!'
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
                        <input type="text" className="form-control" name="username" id="username" aria-describedby="emailHelpId" placeholder="Email" required/>
                        
                    </div>
                    <br/>
                    <div className="form-group">
                      <label>Password</label>
                      <input type="password" className="form-control" name="password" id="password" placeholder="Password" required/>
                    </div>
                    <br/>
                    <input type="submit" value="Login"/>
                    <br></br>
                    {this.state.err !== '' ? this.state.err :''}
                    
                </form>
            </div>
        );
    }
}

export default LoginComponent;