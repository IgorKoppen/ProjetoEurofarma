export interface TokenResponse {
    id: number;
    employeeRegistration: string;
    name: string,
    roles: string[],
    authenticated: boolean,
    created: number,
    expiration: number,
    accessToken: string,
    refreshToken: string,
    instructorId?: number
}