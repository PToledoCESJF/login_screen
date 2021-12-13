import { Api } from "../../service/api";
import { iUser } from "./types";

export function setUserLocalStorage(user: iUser | null){
  localStorage.setItem("US", JSON.stringify(user));
}

export function getUserLocalStorage(){
  const json = localStorage.getItem("US");
  if(!json) return null;
  const user = JSON.parse(json)
  return user ?? null;
}

export async function LoginRequest(email: string, password: string) {
  try {
    const request = await Api.post("login", { email, password });
    return request.data;
  } catch (error) {
    return null;    
  }
}

export async function ForgotRequest(email: string) {
  try {
    const request = await Api.post("auth/forgot", { email });
    return request.data;
  } catch (error) {
    return null;    
  }
}