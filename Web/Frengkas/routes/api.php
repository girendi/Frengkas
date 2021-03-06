<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:api');

Route::post('login', 'API\UserController@login');
Route::post('register', 'API\UserController@register');
Route::get('user/{id}', 'API\UserController@show');

Route::get('/service/{id?}', 'ServiceController@index');
Route::post('/service', 'ServiceController@store');
Route::post('/service/{id}', 'ServiceController@update');
Route::post('/delete/service/{id}', 'ServiceController@destroy');

Route::get('/category/{id_service?}', 'CategoryController@index');
Route::get('/show/category/{id}', 'CategoryController@show');
Route::post('/category', 'CategoryController@store');
Route::post('/category/{id}', 'CategoryController@update');
Route::post('/delete/category/{id}', 'CategoryController@destroy');

Route::get('/waktu', 'WaktuController@index');
Route::get('/show/waktu/{id}', 'WaktuController@show');
Route::post('/waktu', 'WaktuController@store');
Route::post('/waktu/{id}/', 'WaktuController@update');
Route::post('/delete/waktu/{id}/', 'WaktuController@destroy');

Route::get('/pukul/{id_waktu?}', 'PukulController@index');
Route::get('/show/pukul/{id}', 'PukulController@show');
Route::post('/pukul', 'PukulController@store');
Route::post('/pukul/{id}', 'PukulController@update');
Route::post('/delete/pukul/{id}', 'PukulController@destroy');

Route::get('/order', 'OrderController@index');
Route::get('/orderCustomer/{id}', 'OrderController@orderCustomer');
Route::post('/order', 'OrderController@store');
Route::get('/show/order/{id}', 'OrderController@show');
Route::post('/delete/order/{id}', 'OrderController@destroy');
Route::post('/order/{id}', 'OrderController@update');
