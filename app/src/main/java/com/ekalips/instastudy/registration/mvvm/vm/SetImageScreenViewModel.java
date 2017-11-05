package com.ekalips.instastudy.registration.mvvm.vm;

import android.databinding.ObservableField;

import com.ekalips.instastudy.registration.contract.RegistrationActivityContract;
import com.ekalips.instastudy.registration.contract.SetImageScreenContract;
import com.esafirm.imagepicker.model.Image;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class SetImageScreenViewModel extends SetImageScreenContract.ViewModel {

    private final ObservableField<File> selectedFile = new ObservableField<>(null);
    private final RegistrationActivityContract.ViewModel registrationScreenViewModel;

    @Inject
    public SetImageScreenViewModel(RegistrationActivityContract.ViewModel registrationScreenViewModel) {
        this.registrationScreenViewModel = registrationScreenViewModel;
    }

    @Override
    public void skip() {
        registrationScreenViewModel.onImageSkip();
    }

    @Override
    public void setUp() {
        if (selectedFile.get() != null) {
            registrationScreenViewModel.onImageSet(selectedFile.get());
        }
    }

    @Override
    public void setFromCamera() {
        if (view != null) {
            view.requestImageFromCamera();
        }
    }

    @Override
    public void setFromGallery() {
        if (view != null) {
            view.requestImageFromGallery();
        }
    }

    @Override
    public void onImageSelected(Image image) {
        selectedFile.set(new File(image.getPath()));
    }

    @Override
    public ObservableField<File> getSelectedImage() {
        return selectedFile;
    }
}
