import { request } from "@/utlis/request";
export function findBookAll(data) {
    return request({
        url: "/book/selectAll",
        method:"post",
        data
    });
}

export function addBook(data) {
    return request({
        url: "/book/insertBook",
        method: "post",
        data
    })
}

export function deleteBook(id) {
    return request({
        url: "/book/deleteBook",
        params:{
            booknum: id
        },
        method: 'delete'
    })
}

export function selectBook(queryString) {
    return request({
        url: "/book/selectBook",
        method: 'get',
        params: queryString
    })
}

export function editBook(data) {
    return request({
        url: "/book/updateBook",
        method: "put",
        data
    })
}
