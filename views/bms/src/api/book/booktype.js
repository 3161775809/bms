import { request } from "@/utlis/request";
export function findBookTypeAll() {
    return request({
        url: "/booktype/selectBooktypeAll",
        method:"get",
    });
}

export function findBookType(type) {
    return request({
        url: "/booktype/selectBooktype",
        params:type
    })
}

export function findBookTypePage(data) {
    return request({
        url: "/booktype/selectBooktypePage",
        method:"post",
        data
    });
}

export function addBookType(data) {
    return request({
        url: "/booktype/insertBooktype",
        method: 'post',
        data
    })
}

export function editBookType(data) {
    return request({
        url: "/booktype/updateBooktype",
        method: "post",
        data
    })
}

export function deleteBookType(params) {
    return request({
        url: '/booktype/deleteBooktype',
        method: 'get',
        params: {
            type: params
        }
    })
}