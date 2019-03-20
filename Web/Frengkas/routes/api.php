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

Route::get('/service/{id?}', 'ServiceController@index');
Route::post('/service', 'ServiceController@store');
Route::post('/service/{id}', 'ServiceController@update');
Route::delete('/service/{id}', 'ServiceController@destroy');

Route::get('/category/{id_service?}', 'CategoryController@index');
Route::get('/show/category/{id}', 'CategoryController@show');
Route::post('/category', 'CategoryController@store');
Route::post('/category/{id}', 'CategoryController@update');
Route::delete('/category/{id}', 'CategoryController@destroy');
