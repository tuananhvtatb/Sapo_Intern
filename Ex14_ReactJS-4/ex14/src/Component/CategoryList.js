import React, { Component } from 'react';
import axios from 'axios';
import CategoryItem from './CategoryItem';
import { Link } from 'react-router-dom';
import Logout from './Logout';

class CategoryList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            currentPage: 1,
            totalPage: 1000,
        };

    };


    componentDidMount() {
        axios.get('/admin/categories?page=' + 0)
            .then(res => {
                const categories = res.data;
                const total = Math.ceil(categories.length / 5);
                this.setState({ totalPage: total });
            })
            .catch(error => console.log(error));

        axios.get('/admin/categories?page=' + this.state.currentPage)
            .then(res => {
                const categories = res.data;
                this.setState({ categories: categories });
            })
            .catch(error => console.log(error));
    }

    componentDidUpdate(preProps, preState) {
        if (preState.currentPage !== this.state.currentPage) {
            axios.get('/admin/categories?page=' + this.state.currentPage)
                .then(res => {
                    const categories = res.data;
                    this.setState({ categories: categories });
                })
                .catch(error => console.log(error));
        }
    }
    onChange = page => {
        if (page < this.state.totalPage) {
            this.setState({ currentPage: page })
        } else {
            const t = this.state.totalPage - 1;
            this.setState({ currentPage: t })
        }
    }

    render() {
        
        
        let elements = this.state.categories.map((category, index) => {
            return <CategoryItem key={category.id}
                id = {category.id}
                code={category.codeCategory}
                name={category.name}
                description={category.description}
                createdDate = {category.createdDate}
                updatedDate = {category.updatedDate}
                history = {this.props.history}
                />
        });
        return (
            <div>
                <Logout history={this.props.history}/> 
                <div className="container ">
                    <div className="wrap-table">
                        <Link to="/categories/add" className="btn btn-primary mb-3">Thêm danh mục</Link>
                        <table className="table table-striped table-inverse table-responsive">
                            <thead className="thead-inverse">
                                <tr>
                                    <th scope="row">Mã danh mục</th>
                                    <th scope="row">Tên danh mục</th>
                                    <th scope="row">Ngày tạo</th>
                                    <th scope="row">Ngày cập nhật</th>
                                </tr>
                            </thead>
                            <tbody>
                                {elements}
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <ul className="pagination justify-content-center">
                                <li className={this.state.currentPage - 2 < 0 ? 'disabled page-item' : ''}>
                                    <button className="page-link " onClick={() => { this.onChange(this.state.currentPage - 1) }} aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </button>
                                </li>
                                <li className="page-item active"><button className="page-link" onClick={() => { this.onChange(this.state.currentPage) }}>{this.state.currentPage}</button></li>
                                <li className="page-item"><button className={this.state.currentPage + 1 >= this.state.totalPage ? 'page-link d-none' : 'page-link'} onClick={() => { this.onChange(this.state.currentPage + 1) }}>{this.state.currentPage + 1}</button></li>
                                <li className="page-item">
                                    <button className={this.state.currentPage + 1 > this.state.totalPage ? 'disabled page-link' : 'page-link'} onClick={() => { this.onChange(this.state.currentPage + 1) }} aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span className="sr-only">Next</span>
                                    </button>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        );


    }

}
export default CategoryList;