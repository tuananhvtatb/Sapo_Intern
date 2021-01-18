import React, { Component } from 'react'

class CategoryDetail extends Component {

    constructor(props) {
        super(props);
        // //console.log(this.props.match.params.id);
        this.state = {
            id : this.props.match.params.id
        };
    }

    
    render() {

        var id = this.state.id;

        var categories = [
            {
                id: 1,
                name: 'Quả táo vàng',
                description: 'Màu vàng'
            },
            {
                id: 3,
                name: 'Quả táo xanh',
                description: 'Màu xanh'
            },
            {
                id: 2,
                name: 'Quả táo đỏ',
                description: 'Màu đỏ'
            }
        ];


        let elements = categories.find(cate => cate.id == id)
        return (
            <div className="card-columns">
                <div className="card">
                    <div className="card-body">
                    <p>{elements.id}</p>
                        <h4 className="card-title">{elements.name}</h4>
                        <p className="card-text">{elements.description}</p>
                    </div>
                </div>
            </div>
            
        );
    }
}

export default CategoryDetail;