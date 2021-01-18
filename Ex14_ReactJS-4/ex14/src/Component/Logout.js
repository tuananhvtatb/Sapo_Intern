import React, { Component } from 'react'
import { getUser } from '../Utils/Common';
import { removeUserSession} from '../Utils/Common';


export default class Logout extends Component {
    

    handleLogout = () => {
        removeUserSession();
        this.props.history.push('/login');
    }

    render() {

        var user = getUser();

        return (
            
            <div>
                <div className="float-right">
                    Welcome {user.username}!<br /><br />
                    <input type="button" onClick={() => this.handleLogout()} value="Logout" />
                </div>
            </div>
        )
    }
}
