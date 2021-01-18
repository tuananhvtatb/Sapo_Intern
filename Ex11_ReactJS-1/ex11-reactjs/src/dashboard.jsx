import React from 'react'

class DashboardComponent extends React.Component {


    render() {
        var username = this.props.match.params.username;
        return (
            <div>
               <h3>welcome {username}</h3> 
            </div>
        );
    }
}

export default DashboardComponent;