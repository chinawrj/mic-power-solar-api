package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void executeOperations(java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r11, boolean r12) {
        /*
            r10 = this;
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r6 = r1
            r7 = r6
        L7:
            boolean r1 = r0.hasNext()
            r8 = 1
            if (r1 == 0) goto L44
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.SpecialEffectsController$Operation r1 = (androidx.fragment.app.SpecialEffectsController.Operation) r1
            androidx.fragment.app.Fragment r2 = r1.getFragment()
            android.view.View r2 = r2.mView
            androidx.fragment.app.SpecialEffectsController$Operation$State r2 = androidx.fragment.app.SpecialEffectsController.Operation.State.from(r2)
            int[] r3 = androidx.fragment.app.DefaultSpecialEffectsController.AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State
            androidx.fragment.app.SpecialEffectsController$Operation$State r4 = r1.getFinalState()
            int r4 = r4.ordinal()
            r3 = r3[r4]
            if (r3 == r8) goto L3c
            r4 = 2
            if (r3 == r4) goto L3c
            r4 = 3
            if (r3 == r4) goto L3c
            r4 = 4
            if (r3 == r4) goto L36
            goto L7
        L36:
            androidx.fragment.app.SpecialEffectsController$Operation$State r3 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r2 == r3) goto L7
            r7 = r1
            goto L7
        L3c:
            androidx.fragment.app.SpecialEffectsController$Operation$State r3 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r2 != r3) goto L7
            if (r6 != 0) goto L7
            r6 = r1
            goto L7
        L44:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r11)
            java.util.Iterator r11 = r11.iterator()
        L57:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L95
            java.lang.Object r2 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r2 = (androidx.fragment.app.SpecialEffectsController.Operation) r2
            androidx.core.os.CancellationSignal r4 = new androidx.core.os.CancellationSignal
            r4.<init>()
            r2.markStartedSpecialEffect(r4)
            androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo r5 = new androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo
            r5.<init>(r2, r4, r12)
            r0.add(r5)
            androidx.core.os.CancellationSignal r4 = new androidx.core.os.CancellationSignal
            r4.<init>()
            r2.markStartedSpecialEffect(r4)
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r5 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo
            r9 = 0
            if (r12 == 0) goto L83
            if (r2 != r6) goto L86
            goto L85
        L83:
            if (r2 != r7) goto L86
        L85:
            r9 = r8
        L86:
            r5.<init>(r2, r4, r12, r9)
            r3.add(r5)
            androidx.fragment.app.DefaultSpecialEffectsController$1 r4 = new androidx.fragment.app.DefaultSpecialEffectsController$1
            r4.<init>()
            r2.addCompletionListener(r4)
            goto L57
        L95:
            r2 = r10
            r4 = r1
            r5 = r12
            java.util.Map r11 = r2.startTransitions(r3, r4, r5, r6, r7)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r8)
            boolean r12 = r11.containsValue(r12)
            r10.startAnimations(r0, r1, r12, r11)
            java.util.Iterator r11 = r1.iterator()
        Lab:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lbb
            java.lang.Object r12 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r12 = (androidx.fragment.app.SpecialEffectsController.Operation) r12
            r10.applyContainerChanges(r12)
            goto Lab
        Lbb:
            r1.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (final AnimationInfo animationInfo : list) {
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operation);
                            }
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view);
                                    if (z3) {
                                        operation.getFinalState().applyState(view);
                                    }
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            final AnimationInfo animationInfo2 = (AnimationInfo) it.next();
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view2.clearAnimation();
                        container.endViewTransition(view2);
                        animationInfo2.completeSpecialEffect();
                    }
                });
            }
        }
    }

    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> list, List<SpecialEffectsController.Operation> list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        Iterator<TransitionInfo> it;
        View view;
        Object obj;
        ArrayList<View> arrayList;
        Object objMergeTransitionsTogether;
        ArrayList<View> arrayList2;
        HashMap map;
        SpecialEffectsController.Operation operation3;
        View view2;
        Object objMergeTransitionsTogether2;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation4;
        boolean z2;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList<View> arrayList3;
        HashMap map2;
        Rect rect;
        SpecialEffectsController.Operation operation5;
        View view3;
        ArrayList<View> arrayList4;
        DefaultSpecialEffectsController defaultSpecialEffectsController;
        SharedElementCallback exitTransitionCallback;
        SharedElementCallback enterTransitionCallback;
        ArrayList<String> arrayList5;
        View view4;
        final View view5;
        String strFindKeyForValue;
        ArrayList<String> arrayList6;
        DefaultSpecialEffectsController defaultSpecialEffectsController2 = this;
        boolean z3 = z;
        SpecialEffectsController.Operation operation6 = operation;
        SpecialEffectsController.Operation operation7 = operation2;
        HashMap map3 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        boolean z4 = false;
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                map3.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return map3;
        }
        View view6 = new View(getContainer().getContext());
        final Rect rect2 = new Rect();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        boolean z5 = false;
        Object obj2 = null;
        View view7 = null;
        ArrayMap arrayMap3 = arrayMap2;
        for (TransitionInfo transitionInfo3 : list) {
            if (!transitionInfo3.hasSharedElementTransition() || operation6 == null || operation7 == null) {
                arrayMap = arrayMap3;
                operation4 = operation7;
                z2 = z4;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                arrayList3 = arrayList8;
                map2 = map3;
                rect = rect2;
                operation5 = operation6;
                view3 = view6;
                DefaultSpecialEffectsController defaultSpecialEffectsController3 = defaultSpecialEffectsController2;
                arrayList4 = arrayList7;
                defaultSpecialEffectsController = defaultSpecialEffectsController3;
                view7 = view7;
            } else {
                Object objWrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view8 = view7;
                int i = 0;
                while (i < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList<String> arrayList9 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    sharedElementTargetNames = arrayList9;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z3) {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int i2 = 0;
                for (int size = sharedElementSourceNames.size(); i2 < size; size = size) {
                    arrayMap3.put(sharedElementSourceNames.get(i2), sharedElementTargetNames2.get(i2));
                    i2++;
                }
                ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                defaultSpecialEffectsController2.findNamedViews(arrayMap4, operation.getFragment().mView);
                arrayMap4.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap4);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str = sharedElementSourceNames.get(size2);
                        View view9 = arrayMap4.get(str);
                        if (view9 == null) {
                            arrayMap3.remove(str);
                            arrayList6 = sharedElementSourceNames;
                        } else {
                            arrayList6 = sharedElementSourceNames;
                            if (!str.equals(ViewCompat.getTransitionName(view9))) {
                                arrayMap3.put(ViewCompat.getTransitionName(view9), (String) arrayMap3.remove(str));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList6;
                    }
                    arrayList5 = sharedElementSourceNames;
                } else {
                    arrayList5 = sharedElementSourceNames;
                    arrayMap3.retainAll(arrayMap4.keySet());
                }
                final ArrayMap<String, View> arrayMap5 = new ArrayMap<>();
                defaultSpecialEffectsController2.findNamedViews(arrayMap5, operation2.getFragment().mView);
                arrayMap5.retainAll(sharedElementTargetNames2);
                arrayMap5.retainAll(arrayMap3.values());
                if (exitTransitionCallback != null) {
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap5);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str2 = sharedElementTargetNames2.get(size3);
                        View view10 = arrayMap5.get(str2);
                        if (view10 == null) {
                            String strFindKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap3, str2);
                            if (strFindKeyForValue2 != null) {
                                arrayMap3.remove(strFindKeyForValue2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view10)) && (strFindKeyForValue = FragmentTransition.findKeyForValue(arrayMap3, str2)) != null) {
                            arrayMap3.put(strFindKeyForValue, ViewCompat.getTransitionName(view10));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap3, arrayMap5);
                }
                defaultSpecialEffectsController2.retainMatchingViews(arrayMap4, arrayMap3.keySet());
                defaultSpecialEffectsController2.retainMatchingViews(arrayMap5, arrayMap3.values());
                if (arrayMap3.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    arrayMap = arrayMap3;
                    arrayList3 = arrayList8;
                    rect = rect2;
                    view3 = view6;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view7 = view8;
                    obj2 = null;
                    z2 = false;
                    operation4 = operation2;
                    map2 = map3;
                    operation5 = operation;
                    DefaultSpecialEffectsController defaultSpecialEffectsController4 = defaultSpecialEffectsController2;
                    arrayList4 = arrayList7;
                    defaultSpecialEffectsController = defaultSpecialEffectsController4;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z3, arrayMap4, true);
                    HashMap map4 = map3;
                    arrayMap = arrayMap3;
                    View view11 = view6;
                    ArrayList<View> arrayList10 = arrayList8;
                    arrayList4 = arrayList7;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap5, false);
                        }
                    });
                    arrayList4.addAll(arrayMap4.values());
                    if (arrayList5.isEmpty()) {
                        z2 = false;
                        view7 = view8;
                    } else {
                        z2 = false;
                        View view12 = arrayMap4.get(arrayList5.get(0));
                        fragmentTransitionImpl2.setEpicenter(objWrapTransitionInSet, view12);
                        view7 = view12;
                    }
                    arrayList10.addAll(arrayMap5.values());
                    if (sharedElementTargetNames2.isEmpty() || (view5 = arrayMap5.get(sharedElementTargetNames2.get(z2 ? 1 : 0))) == null) {
                        defaultSpecialEffectsController = this;
                        view4 = view11;
                    } else {
                        defaultSpecialEffectsController = this;
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl2.getBoundsOnScreen(view5, rect2);
                            }
                        });
                        view4 = view11;
                        z5 = true;
                    }
                    fragmentTransitionImpl2.setSharedElementTargets(objWrapTransitionInSet, view4, arrayList4);
                    rect = rect2;
                    view3 = view4;
                    arrayList3 = arrayList10;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList3);
                    operation5 = operation;
                    map2 = map4;
                    map2.put(operation5, true);
                    operation4 = operation2;
                    map2.put(operation4, true);
                    obj2 = objWrapTransitionInSet;
                }
            }
            z3 = z;
            z4 = z2;
            rect2 = rect;
            view6 = view3;
            arrayList8 = arrayList3;
            operation6 = operation5;
            map3 = map2;
            operation7 = operation4;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            arrayMap3 = arrayMap;
            ArrayList<View> arrayList11 = arrayList4;
            defaultSpecialEffectsController2 = defaultSpecialEffectsController;
            arrayList7 = arrayList11;
        }
        View view13 = view7;
        ArrayMap arrayMap6 = arrayMap3;
        SpecialEffectsController.Operation operation8 = operation7;
        boolean z6 = z4;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        ArrayList<View> arrayList12 = arrayList8;
        HashMap map5 = map3;
        Rect rect3 = rect2;
        SpecialEffectsController.Operation operation9 = operation6;
        View view14 = view6;
        DefaultSpecialEffectsController defaultSpecialEffectsController5 = defaultSpecialEffectsController2;
        ArrayList<View> arrayList13 = arrayList7;
        ArrayList arrayList14 = new ArrayList();
        Iterator<TransitionInfo> it2 = list.iterator();
        Object obj3 = null;
        Object obj4 = null;
        while (it2.hasNext()) {
            TransitionInfo next = it2.next();
            if (next.isVisibilityUnchanged()) {
                it = it2;
                map5.put(next.getOperation(), Boolean.valueOf(z6));
                next.completeSpecialEffect();
            } else {
                it = it2;
                Object objCloneTransition = fragmentTransitionImpl3.cloneTransition(next.getTransition());
                SpecialEffectsController.Operation operation10 = next.getOperation();
                boolean z7 = (obj2 == null || !(operation10 == operation9 || operation10 == operation8)) ? z6 : true;
                if (objCloneTransition == null) {
                    if (!z7) {
                        map5.put(operation10, Boolean.valueOf(z6));
                        next.completeSpecialEffect();
                    }
                    arrayList = arrayList13;
                    view = view14;
                    arrayList2 = arrayList12;
                    objMergeTransitionsTogether2 = obj3;
                    objMergeTransitionsTogether = obj4;
                    map = map5;
                    view2 = view13;
                } else {
                    final ArrayList<View> arrayList15 = new ArrayList<>();
                    Object obj5 = obj3;
                    defaultSpecialEffectsController5.captureTransitioningViews(arrayList15, operation10.getFragment().mView);
                    if (z7) {
                        if (operation10 == operation9) {
                            arrayList15.removeAll(arrayList13);
                        } else {
                            arrayList15.removeAll(arrayList12);
                        }
                    }
                    if (arrayList15.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(objCloneTransition, view14);
                        arrayList = arrayList13;
                        view = view14;
                        arrayList2 = arrayList12;
                        operation3 = operation10;
                        objMergeTransitionsTogether = obj4;
                        map = map5;
                        obj = obj5;
                    } else {
                        fragmentTransitionImpl3.addTargets(objCloneTransition, arrayList15);
                        view = view14;
                        obj = obj5;
                        arrayList = arrayList13;
                        objMergeTransitionsTogether = obj4;
                        arrayList2 = arrayList12;
                        map = map5;
                        fragmentTransitionImpl3.scheduleRemoveTargets(objCloneTransition, objCloneTransition, arrayList15, null, null, null, null);
                        if (operation10.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation10;
                            list2.remove(operation3);
                            ArrayList<View> arrayList16 = new ArrayList<>(arrayList15);
                            arrayList16.remove(operation3.getFragment().mView);
                            fragmentTransitionImpl3.scheduleHideFragmentView(objCloneTransition, operation3.getFragment().mView, arrayList16);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList15, 4);
                                }
                            });
                        } else {
                            operation3 = operation10;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList14.addAll(arrayList15);
                        if (z5) {
                            fragmentTransitionImpl3.setEpicenter(objCloneTransition, rect3);
                        }
                        view2 = view13;
                    } else {
                        view2 = view13;
                        fragmentTransitionImpl3.setEpicenter(objCloneTransition, view2);
                    }
                    map.put(operation3, true);
                    if (next.isOverlapAllowed()) {
                        objMergeTransitionsTogether = fragmentTransitionImpl3.mergeTransitionsTogether(objMergeTransitionsTogether, objCloneTransition, null);
                        objMergeTransitionsTogether2 = obj;
                    } else {
                        objMergeTransitionsTogether2 = fragmentTransitionImpl3.mergeTransitionsTogether(obj, objCloneTransition, null);
                    }
                }
                view13 = view2;
                obj4 = objMergeTransitionsTogether;
                obj3 = objMergeTransitionsTogether2;
                map5 = map;
                view14 = view;
                arrayList13 = arrayList;
                arrayList12 = arrayList2;
                z6 = false;
            }
            it2 = it;
        }
        ArrayList<View> arrayList17 = arrayList13;
        ArrayList<View> arrayList18 = arrayList12;
        HashMap map6 = map5;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj4, obj3, obj2);
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged()) {
                Object transition = transitionInfo4.getTransition();
                SpecialEffectsController.Operation operation11 = transitionInfo4.getOperation();
                boolean z8 = obj2 != null && (operation11 == operation9 || operation11 == operation8);
                if (transition != null || z8) {
                    if (!ViewCompat.isLaidOut(getContainer())) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation11);
                        }
                        transitionInfo4.completeSpecialEffect();
                    } else {
                        fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo4.getOperation().getFragment(), objMergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                            @Override // java.lang.Runnable
                            public void run() {
                                transitionInfo4.completeSpecialEffect();
                            }
                        });
                    }
                }
            }
        }
        if (!ViewCompat.isLaidOut(getContainer())) {
            return map6;
        }
        FragmentTransition.setViewVisibility(arrayList14, 4);
        ArrayList<String> arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList18);
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList17, arrayList18, arrayListPrepareSetNameOverridesReordered, arrayMap6);
        FragmentTransition.setViewVisibility(arrayList14, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj2, arrayList17, arrayList18);
        return map6;
    }

    void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (arrayList.contains(view)) {
                    return;
                }
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        if (arrayList.contains(view)) {
            return;
        }
        arrayList.add(view);
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State stateFrom = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return stateFrom == finalState || !(stateFrom == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
            this.mIsPop = z;
        }

        FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = animationOrAnimatorLoadAnimation;
            this.mLoadedAnim = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            super(operation, cancellationSignal);
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
