import React, { Component } from 'react';
import axios from 'axios';

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
                this.setState({totalPage : total   });
                console.log(this.state.totalPage)
            })
            .catch(error => console.log(error));

        axios.get('/admin/categories?page=' + this.state.currentPage)
            .then(res => {
                const categories = res.data;
                this.setState({ categories : categories});
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
        if(page < this.state.totalPage){
            this.setState({ currentPage: page })
        }else{
            const t = this.state.totalPage -1;
            this.setState({ currentPage: t })
        }
    }

    render() {
        let elements = this.state.categories.map((category, index) => {
            return <tr key={category.id}>
                <td >{category.id}</td>
                <td >{category.name}</td>
                <td >{category.description}</td>
            </tr>;
        });
        return (
            <div className="container ">
                <table className="table table-striped table-inverse table-responsive">
                    <thead className="thead-inverse">
                        <tr>
                            <th scope="row">Mã danh mục</th>
                            <th scope="row">Tên danh mục</th>
                            <th scope="row">Mô tả</th>
                        </tr>
                    </thead>
                    <tbody>
                        {elements}
                    </tbody>
                </table>
                <nav aria-label="Page navigation">
                    <ul className="pagination justify-content-center">
                        <li className={this.state.currentPage - 2 < 0 ? 'disabled page-item' : ''}>
                            <a className="page-link " onClick={() => { this.onChange(this.state.currentPage - 1) }} aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li className="page-item active"><a className="page-link" onClick={() => { this.onChange(this.state.currentPage ) }}>{this.state.currentPage }</a></li>
                        <li className="page-item"><a className={this.state.currentPage + 1 >= this.state.totalPage ? 'page-link d-none' : 'page-link'} onClick={() => { this.onChange(this.state.currentPage + 1) }}>{this.state.currentPage+1}</a></li>
                        <li className="page-item">
                            <a className={this.state.currentPage +1 > this.state.totalPage? 'disabled page-link' : 'page-link'} onClick={() => { this.onChange(this.state.currentPage + 1) }} aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span className="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

        );


    }

}
export default CategoryList;