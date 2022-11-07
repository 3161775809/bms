import { request } from "@/utlis/request";
export function findBorrowerPage(data) {
    return request({
        url: '/borrower/findPage',
        method: 'post',
        data
    })
}

export function insertBorrower(data) {
    return request({
        url: '/borrower/insertBorrower',
        method: 'post',
        data
    })
}

export function editBorrower(data) {
    return request({
        url: '/borrower/updateBorrower',
        method: 'put',
        data
    })
}

export function deleteBorrower(data) {
    return request({
        url: '/borrower/deleteBorrower',
        method: 'delete',
        data
    })
}