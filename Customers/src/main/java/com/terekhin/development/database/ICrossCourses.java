package com.terekhin.development.database;

import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.helpers.NotificationService;

import java.util.List;

public interface ICrossCourses extends IRepository<CrossCourse,Long>  {
    List<CrossCourse> getAllByCurrencyId(long currency_id) throws NotificationService;
}
