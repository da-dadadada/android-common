/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package osp.leobert.android.common.domain.interactors;

import android.support.annotation.NonNull;


import java.util.List;

import osp.leobert.android.common.domain.interactors.base.AbsDbInteractor;
import osp.leobert.android.common.domain.repository.BaseRepository;
import osp.leobert.android.common.domain.repository.Repository;

/**
 * <p><b>Package:</b> com.lht.creationspace.base.domain.interactors </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> DbInteractorFactory </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/8/4.
 */

public class DbInteractorFactory<Model> {

    public static class NumKeyDbInteractorFactory<Model> extends DbInteractorFactory<Model> {
        private BaseRepository.NumKeyDbRepository<Model> numKeyRepository;

        protected NumKeyDbInteractorFactory(BaseRepository.NumKeyDbRepository<Model> repository) {
            super(repository);
            this.numKeyRepository = repository;
        }

        public final AbsDbInteractor<Model> newQueryByIdInteractor(final long key,
                                                                   BaseRepository.OnQueryTaskFinishListener<Model> listener) {
            return new AbsDbInteractor<Model>(listener) {
                @Override
                protected Model runTask() {
                    return numKeyRepository.queryById(key);
                }
            };
        }
    }

    public static class StringKeyDbInteractorFactory<Model> extends DbInteractorFactory<Model> {
        private BaseRepository.StringKeyDbRepository<Model> stringKeyRepository;

        protected StringKeyDbInteractorFactory(BaseRepository.StringKeyDbRepository<Model> repository) {
            super(repository);
            this.stringKeyRepository = repository;
        }

        public final AbsDbInteractor<Model> newQueryByIdInteractor(final String key,
                                                                   BaseRepository.OnQueryTaskFinishListener<Model> listener) {
            return new AbsDbInteractor<Model>(listener) {
                @Override
                protected Model runTask() {
                    return stringKeyRepository.queryById(key);
                }
            };
        }
    }

    ////////##############################################
    ////////##############################################


    private Repository<Model> repository;

    private DbInteractorFactory(Repository<Model> repository) {
        this.repository = repository;
    }

    public static <M> DbInteractorFactory<M>
    getInstance(@NonNull Repository<M> repository) {
        return new DbInteractorFactory<>(repository);
    }

    public static <M> NumKeyDbInteractorFactory<M>
    getNumKDIFInstance(@NonNull BaseRepository.NumKeyDbRepository<M> repository) {
        return new NumKeyDbInteractorFactory<>(repository);
    }

    public static <M> StringKeyDbInteractorFactory<M>
    getStringKDIFInstance(@NonNull BaseRepository.StringKeyDbRepository<M> repository) {
        return new StringKeyDbInteractorFactory<>(repository);
    }

    public final AbsDbInteractor<Void> newSaveOrUpdateInteractor(final Model model) {
        return new AbsDbInteractor<Void>() {
            @Override
            protected Void runTask() {
                return repository.saveOrUpdate(model);
            }
        };
    }

    public final AbsDbInteractor<Void> newSaveOrUpdateInteractor(final Model model,
                                                                 BaseRepository.SimpleOnTaskFinishListener listener) {
        return new AbsDbInteractor<Void>(listener) {
            @Override
            protected Void runTask() {
                return repository.saveOrUpdate(model);
            }
        };
    }

    public final AbsDbInteractor<List<Model>> newQueryAllInteractor(AbsDbInteractor.OnTaskFinishListener<List<Model>> listener) {
        return new AbsDbInteractor<List<Model>>(listener) {
            @Override
            protected List<Model> runTask() {
                return repository.queryAll();
            }
        };
    }

    public final AbsDbInteractor<Void> newDeleteAllInteractor(BaseRepository.SimpleOnTaskFinishListener listener) {
        return new AbsDbInteractor<Void>(listener) {
            @Override
            protected Void runTask() {
                return repository.deleteAll();
            }
        };
    }


    public final AbsDbInteractor<Void> newDeleteInteractor(final Model model) {
        return new AbsDbInteractor<Void>() {
            @Override
            protected Void runTask() {
                return repository.delete(model);
            }
        };
    }

    public final AbsDbInteractor<Void> newDeleteInteractor(final Model model,
                                                           BaseRepository.SimpleOnTaskFinishListener listener) {
        return new AbsDbInteractor<Void>(listener) {
            @Override
            protected Void runTask() {
                return repository.delete(model);
            }
        };
    }


}
