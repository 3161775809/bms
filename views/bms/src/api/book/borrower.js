import { request } from "@/utlis/request";

export function selectAll() {
    return request({
        url: '/borrower/findAll',
        method: 'get'
    })
}