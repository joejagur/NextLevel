//@ts-ignore
import { Games } from '/games.ts';

export interface User {
    id: number;
    username: string;
    password: string;
    games: Games[];
    
  
}