import request from '@/utils/request'

export function getList() {
    return request({
        url: '/promotion/all',
        method: 'get'
    });
}