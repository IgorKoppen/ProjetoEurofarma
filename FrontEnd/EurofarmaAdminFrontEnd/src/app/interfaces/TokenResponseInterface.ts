export interface TokenResponse {
    id: number;
    employeeRegistration: string;
    name: string,
    roles: string[],
    authenticated: boolean,
    created: string,
    expiration: string,
    accessToken: string,
    refreshToken: string,
    instructorId?: number
}