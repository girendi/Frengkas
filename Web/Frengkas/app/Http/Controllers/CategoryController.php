<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;
use App\Service;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id_service = null)
    {
      $categories = Category::Where('id_service', '=', $id_service)->orderBy('price', 'asc')->get();
      $service = Service::find($id_service);
      return response()->json(['Service' => $service, 'Category' => $categories]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $category = new Category;
        $category->id_service = $request->input('id_service');
        $category->name = $request->input('name');
        $category->price = $request->input('price');
        $category->save();
        return response()->json($category);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return Category::find($id);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
      $category = Category::find($id);
      $category->name = $request->input('name');
      $category->price = $request->input('price');
      $category->save();
      return response()->json($category);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
      $category = Category::find($id)->delete();
      return 'Category successfully deleted';
    }
}
