import { STATUS_SUCCESS } from './http.contants';

export const isStatus2xx = (status: number) => STATUS_SUCCESS.includes(status);
