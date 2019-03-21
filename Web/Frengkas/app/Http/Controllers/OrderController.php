<?php

namespace App\Http\Controllers;

use App\Pukul;
use Illuminate\Http\Request;
use App\Order;

class OrderController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $orders = Order::orderBy('id', 'asc')->get();
        return response()->json(['Order' => $orders]);
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
        $order = New Order;
        $order->id_user = $request->input('id_user');
        $order->id_pukul = $request->input('id_pukul');
        $order->id_category = $request->input('id_category');
        $order->location = $request->input('location');
        $order->save();

        $pukul = Pukul::find($order->id_pukul);
        $pukul->status = "booking";
        $pukul->save();
        return response()->json($order);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return Order::find($id);
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
        $order = Order::find($id);
        $order->location = $request->input('location');
        $order->save();
        return 'Order successfully updated with id ' . $order->id;
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $order= Order::find($id);
        $pukul = Pukul::find($order->id_pukul);
        $pukul->status = "free";
        $pukul->save();
        $order->delete();
        return 'Order successfully deleted';
    }
}
