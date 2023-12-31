import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TeacherPreviewDto} from "../models/TeacherPreviewDto";
import {TeacherCardDto} from "../models/TeacherCardDto";
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class TeacherService {

    constructor(private readonly http: HttpClient) {
    }

    public getTopTeachersList(): Observable<TeacherPreviewDto[]> {
        return this.http.get<TeacherPreviewDto[]>(`${environment.BASE_API_URL}teachers/topten`);
    }

    public getTeacherById(id: string): Observable<TeacherCardDto> {
        return this.http.get<TeacherCardDto>(`${environment.BASE_API_URL}teachers/${id}`)
    }

    teacher$ = (id: string): Observable<TeacherCardDto> =>
        this.http.get<TeacherCardDto>(`${environment.BASE_API_URL}teachers/${id}`)

}
