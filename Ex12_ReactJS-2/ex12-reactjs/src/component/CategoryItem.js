import React, { Component } from 'react'

class CategoryItem extends Component {

    onClickLink(id){
        this.props.history.push('/category/'+id);
    }

    render() {
        return (
            <div>
                <a className="btn btn-primary" onClick = {() => {this.onClickLink(this.props.id)}}><h5>{this.props.name}</h5></a>
            </div>
        );
    }
}

export default CategoryItem;