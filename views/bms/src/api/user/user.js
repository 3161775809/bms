import { request } from "@/utlis/request"
export function findAllUser() {
    return request({
        url: '/user/findAllUser',
        method: 'get'
    })
}

export function deleteUser(data) {
    return request({
        url: '/user/deleteUser',
        method: 'post',
        data
    })
}

export function editUser(data) {
    return request({
        url: '/user/updateUser',
        method: 'post',
        data
    })
}

export function register(data) {
    return request({
        url: '/user/insertUser',
        method: 'post',
        data
    })
}

export function findUserPage(data) {
    return request({
        url: '/user/findPage',
        method: 'post',
        data
    })
}