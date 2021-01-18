import React, { Component } from 'react'

export default class CategoryItem extends Component {


    getDetail() {
        this.props.history.push('/categories/add/'+this.props.id);
    }

    render() {
        return (
            <tr onClick={this.getDetail.bind(this)}>
                <td >{this.props.code}</td>
                <td >{this.props.name}</td>
                <td>{this.props.createdDate}</td>
                <td>{this.props.updatedDate}</td>
            </tr>
        )
    }
}
