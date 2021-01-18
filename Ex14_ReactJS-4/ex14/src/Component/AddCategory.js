import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios';

import Logout from './Logout'

export default class AddCategory extends Component {

    constructor(props) {
        super(props);
        this.state = {
            codeCategory: '',
            name: '',
            description: '',
            statusCreate: true,
            id: this.props.match.params.id,
        }
    }


    // Lấy dữ liệu khi nhập form
    handleChange = event => {

        var target = event.target;
        var name = target.name;
        var value = target.value;


        this.setState({
            [name]: value
        });
    }

    // Lấy thông tin cũ để cập nhật
    handleValue = event => {

        var target = event.target;
        var name = target.name;
        var value = target.value;


        this.setState({
            [name]: value
        });
    }

    handleSubmit = event => {
        event.preventDefault();

        const category = {
            codeCategory: this.state.codeCategory,
            name: this.state.name,
            description: this.state.description
        };

        axios.post('/admin/categories', category)
            .then(res => {
                if (res.status === 201) {
                    alert('Thêm mới thành công!')
                    this.setState({
                        status: true
                    })
                }
            })
            .catch(error => {
                this.setState({ status: false });
            }
            );
    }

    componentDidMount() {

        if (this.state.id === undefined) {
            return
        } else {
            axios.get('/admin/categories/' + this.state.id).then((res) => {
                let category = res.data;
                this.setState({
                    codeCategory: category.codeCategory,
                    name: category.name,
                    description: category.description
                });
            });
        }
    }

    saveOrUpdateCategory = (e) => {
        e.preventDefault();
        let category = {
            codeCategory: this.state.codeCategory,
            name: this.state.name,
            description: this.state.description
        };
        // step 5
        if (this.state.id === undefined) {
            axios.post('/admin/categories', category)
                .then(res => {
                    if (res.status === 201) {
                        alert('Thêm mới thành công!')
                        this.setState({
                            statusCreate: true
                        })
                    }
                })
                .catch(error => {
                    console.log(error.response.statusText);
                    this.setState({ statusCreate: false });
                });
        } else {
            axios.put('/admin/categories/' + this.state.id, category)
                .then(res => {
                    if (res.status === 200) {
                        alert('Sửa thành công!');
                        this.props.history.goBack();
                        this.setState({
                            statusCreate: true
                        })
                        console.log(this.state.statusCreate)
                    }
                })
                .catch(error => {
                    console.log(error.response.statusText);
                    this.setState({ statusCreate: false });
                });
        }
    }


    render() {
        return (

            <div className="container">
                <Logout history={this.props.history}/>

                <h1>{this.state.id !== undefined ? 'Sửa danh mục' : 'Thêm danh mục'}</h1>
                <p className="text-danger">{this.state.statusCreate === true? '' : 'Có lỗi xảy ra!'}</p>
                <form onSubmit={this.saveOrUpdateCategory.bind(this)} method="post">
                    <div className="form-group">
                        <label htmlFor="codeCategory">Mã danh mục </label>
                        <input type="text" name="codeCategory" id="codeCategory" className="form-control" placeholder="Mã danh mục" required
                            onChange={this.handleChange} value={this.state.codeCategory} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Tên danh mục </label>
                        <input type="text" name="name" id="name" className="form-control" placeholder="Tên danh mục" required
                            onChange={this.handleChange} value={this.state.name}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Mô tả </label>
                        <input type="text" name="description" id="description" className="form-control" placeholder="Mô tả" required
                            onChange={this.handleChange} value={this.state.description}
                        />
                    </div>
                    <div>
                        <button type="submit" className="btn btn-primary mr-2">{this.state.id !== undefined ? 'Sửa đổi' : 'Thêm mới'}</button>
                        <Link to="/categories" className="btn btn-danger">Hủy bỏ</Link>
                    </div>
                </form>
            </div>
        )
    }
}
