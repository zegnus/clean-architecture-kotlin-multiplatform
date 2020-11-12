//
//  ContentView.swift
//  CleanArchitecture
//
//  Created by Ferran Garriga on 03/11/2020.
//

import SwiftUI


struct ContentView: View {
    var body: some View {
        Button(action: {
            print("hello world 3")
            let contentPresenter: ContentPresenter = ContentPresenter()
            contentPresenter.start { text in
                print(text)
            }
            
        }) {
            Text("Hello world 3")
        }
    }
//        var contentPresenter: ContentPresenter = ContentPresenter()
//        contentPresenter.startPresenting { (state) in
//            print(state.text)
//        }
//        contentPresenter.action(action: ContentUseCase.ActionRefresh())
//        return Text("Hello")
//            .padding()
//    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
