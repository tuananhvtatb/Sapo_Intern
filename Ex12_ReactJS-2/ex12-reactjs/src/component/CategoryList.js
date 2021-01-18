import React, { Component } from 'react'
import CategoryItem from './CategoryItem'

class CategoryList extends Component {

    constructor(){
        super();
        this.state={
            err:''
        };
    }

    render() {

        var categories = [
            {
                id : 1,
                name : 'Quả táo vàng',
                description : 'Màu vàng'
            },
            {
                id : 2,
                name : 'Quả táo xanh',
                description : 'Màu xanh'
            },
            {
                id : 3,
                name : 'Quả táo đỏ',
                description : 'Màu đỏ'
            }
        ];

        let elements = categories.map((category , index) => {
            return <CategoryItem 
                key = {category.id}
                id = {category.id}
                name = {category.name}
                description = {category.description}
                history= {this.props.history}
            />
        });

        return (
            <div>
                
                <div className="dropdown container">
                    <button className="btn-center btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Danh mục
                    <span className="caret"></span></button>
                    <ul className="dropdown-menu">
                        {elements}
                    </ul>
                </div>
            </div>
            
        );
    }
}

export default CategoryList;