import axios from "axios";
import { getUserLocalStorage } from "../context/AuthProvider/utils";

export const Api = axios.create({
  baseURL: 'http://localhost:8080/'
});

Api.interceptors.request.use(
  (config: any) => {
    const user = getUserLocalStorage();
    config.headers.Authorization = user?.token;
    return config;
  },
  (error: any) => {
    return Promise.reject(error)
  }
)
