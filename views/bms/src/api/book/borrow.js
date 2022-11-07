import { request } from "@/utlis/request";
export function findBorrowAll() {
    return request({
        url: "/borrlend/selectBorrLend",
        method:"get"
    });
}

export function findBorrowPage(data) {
    return request({
        url: "/borrlend/selectBorrLendPage",
        method:"post",
        data
    })
}

export function editBorrow(data) {
    return request({
        url: "/borrlend/updateBorrLend",
        method: "post",
        data
    })
}

export function findBorrowBookByCardNum(cardNum) {
    return request({
        url: '/borrlend/findBorrowBookList',
        method: "get",
        params: {
            cardNum: cardNum
        }
    })
}

export function addBorrow(data) {
    return request({
        url: "/borrlend/insertBorrLend",
        method: "post",
        data
    })
}

export function deleteBorrow(params) {
    return request({
        url: "/borrlend/deleteBorrlend",
        method: "get",
        params: {
            booknum: params
        }
    })
}