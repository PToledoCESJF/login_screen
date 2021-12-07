import { createContext } from "react";
import { iContext } from "./types";

export const AuthContext = createContext<iContext>({} as iContext)
