import { request } from "@/utlis/request";
export function login(data) {
    return request({
        url: "/user/login",
        method:"post",
        data
    });
}
